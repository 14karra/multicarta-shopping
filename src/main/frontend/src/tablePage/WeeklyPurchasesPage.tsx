import React, {Component} from "react";
import Pagination from "../pagination/Pagination";
import {concatMap} from "rxjs/operators";
import {from, Subscription, timer} from "rxjs";
import {IItem} from "../model/interfaces";
import {extractJsonFromXml} from "../util/XMLUtil";
import fetchXml from "../client/WebServerClient";
import PurchasesTable from "../table/PurchasesTable";

class WeeklyPurchasesPage extends Component<{}> {
    state = {
        purchases: [],
        currentPage: 0,
        totalPages: 0,
        updateTime: 10000,
        baseApi: "/api/v1/purchase",
        maxPerPage: 10
    };
    private pageSubscription: Subscription | null = null;

    async componentDidMount() {
        fetchXml(this.state.baseApi).then(response =>
            extractJsonFromXml(response).then(jsonData => {
                    let purchases = (jsonData as any).Purchases.Purchase;
                    console.log("Data: " + JSON.stringify(purchases));

                    const totalPages = Math.ceil(purchases.length / this.state.maxPerPage);
                    if (purchases.length > this.state.maxPerPage) purchases = purchases.slice(0, this.state.maxPerPage);
                    this.setState({purchases: purchases, totalPages: totalPages});

                    this.pageSubscription = timer(0, this.state.updateTime)
                        .pipe(concatMap(() => from(this.getData())))
                        .subscribe(data => {
                            console.log("Setting purchases state from " +
                                (this.state.purchases == undefined) ? "undefined" : JSON.stringify(this.state.purchases) + " to " +
                                (data == undefined) ? "undefined" : JSON.stringify(data)
                            );
                            this.setState({
                                purchases: data
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

    async getData(): Promise<IItem[]> {
        let response = await fetch(this.state.baseApi + '?page=' + this.state.currentPage
            + '&size=' + this.state.maxPerPage, {
            method: 'GET',
            headers: {
                'Accept': 'application/xml',
            },
        });

        return extractJsonFromXml(response).then(jsonData => {
            let purchases = (jsonData as any).Purchases.Purchase;
            console.log("Purchases: " + JSON.stringify(purchases));
            return purchases;
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
                    <h1>Еженедельные покупки</h1>
                </div>
                <PurchasesTable purchases={this.state.purchases}/>
                <Pagination totalPages={this.state.totalPages} currentPage={this.state.currentPage}
                            onPrev={this.clickPrevHandler} onNext={this.clickNextHandler}
                            onPage={this.clickPageHandler}/>
            </main>
        )
    }
}

export default WeeklyPurchasesPage;