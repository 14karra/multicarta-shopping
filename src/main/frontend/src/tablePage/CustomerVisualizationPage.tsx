import React, {Component} from "react";
import Pagination from "../pagination/Pagination";
import {concatMap} from "rxjs/operators";
import {from, Subscription, timer} from "rxjs";
import {extractJsonFromXml} from "../util/XMLUtil";
import fetchXml from "../client/WebServerClient";
import CustomersTable from "../table/CustomersTable";
import {ICustomer} from "../model/interfaces";

class CustomerVisualizationPage extends Component<{}> {
    state = {
        customers: [],
        currentPage: 0,
        totalPages: 0,
        updateTime: 10000,
        baseApi: "/api/v1/customer",
        maxPerPage: 10
    };
    private pageSubscription: Subscription | null = null;

    async componentDidMount() {
        fetchXml(this.state.baseApi).then(response =>
            extractJsonFromXml(response).then(jsonData => {
                    let customers = (jsonData as any).Customers.Customer;
                    console.log("Data: " + JSON.stringify(customers));

                    const totalPages = Math.ceil(customers.length / this.state.maxPerPage);
                    if (customers.length > this.state.maxPerPage) customers = customers.slice(0, this.state.maxPerPage);
                    this.setState({customers: customers, totalPages: totalPages});

                    this.pageSubscription = timer(0, this.state.updateTime)
                        .pipe(concatMap(() => from(this.getData())))
                        .subscribe(data => {
                            console.log("Setting customers state from " +
                                (this.state.customers == undefined) ? "undefined" : JSON.stringify(this.state.customers) + " to " +
                                (data == undefined) ? "undefined" : JSON.stringify(data)
                            );
                            this.setState({
                                customers: data
                            });
                        });
                }
            )
        )
    }

    componentWillUnmount(): void {
        if (this.pageSubscription !== null) {
            this.pageSubscription.unsubscribe();
        }
    }

    async getData(): Promise<ICustomer[]> {
        let response = await fetch(this.state.baseApi + '?page=' + this.state.currentPage
            + '&size=' + this.state.maxPerPage, {
            method: 'GET',
            headers: {
                'Accept': 'application/xml',
            },
        });

        return extractJsonFromXml(response).then(jsonData => {
            let customers = (jsonData as any).Customers.Customer;
            console.log("Customers: " + JSON.stringify(customers));
            return customers;
        });
    }

    clickPrevHandler = async () => {
        if (this.state.currentPage <= 0) return;
        const currentPage = this.state.currentPage - 1;
        await this.setState({currentPage});
        this.getData();
    };

    clickNextHandler = async () => {
        if (this.state.currentPage > this.state.totalPages - 2) return;
        const currentPage = this.state.currentPage + 1;
        await this.setState({currentPage});
        this.getData();
    };

    clickPageHandler = async (pageNumber: number) => {
        const currentPage = pageNumber;
        if (currentPage === this.state.currentPage) return;
        await this.setState({currentPage});
        this.getData();
    };

    render() {
        return (
            <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div className={"form-inline"}>
                    <h1>Клиенты</h1>
                </div>
                <CustomersTable customers={this.state.customers}/>
                <Pagination totalPages={this.state.totalPages} currentPage={this.state.currentPage}
                            onPrev={this.clickPrevHandler} onNext={this.clickNextHandler}
                            onPage={this.clickPageHandler}/>
            </main>
        )
    }
}

export default CustomerVisualizationPage;