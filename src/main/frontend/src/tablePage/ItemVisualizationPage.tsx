import React, {Component} from "react";
import Pagination from "../pagination/Pagination";
import {concatMap} from "rxjs/operators";
import {from, Subscription, timer} from "rxjs";
import ItemsTable from "../table/ItemsTable";
import {IItem} from "../model/interfaces";
import {extractJsonFromXml} from "../util/XMLUtil";
import fetchXml from "../client/WebServerClient";

class ItemVisualizationPage extends Component<{}> {
    state = {
        items: [],
        currentPage: 0,
        totalPages: 0,
        updateTime: 10000,
        baseApi: "/api/v1/item",
        maxPerPage: 10
    };
    private pageSubscription: Subscription | null = null;

    async componentDidMount() {
        fetchXml(this.state.baseApi).then(response =>
            extractJsonFromXml(response).then(jsonData => {
                    let items = (jsonData as any).Items.Item;
                    console.log("Data: " + JSON.stringify(items));

                    const totalPages = Math.ceil(items.length / this.state.maxPerPage);
                    if (items.length > this.state.maxPerPage) items = items.slice(0, this.state.maxPerPage);
                    this.setState({items: items, totalPages: totalPages});

                    this.pageSubscription = timer(0, this.state.updateTime)
                        .pipe(concatMap(() => from(this.getData())))
                        .subscribe(data => {
                            console.log("Setting items state from " +
                                (this.state.items == undefined) ? "undefined" : JSON.stringify(this.state.items) + " to " +
                                (data == undefined) ? "undefined" : JSON.stringify(data)
                            );
                            this.setState({
                                items: data
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
            let items = (jsonData as any).Items.Item;
            console.log("Items: " + JSON.stringify(items));
            return items;
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
                    <h1>Продукты</h1>
                </div>
                <ItemsTable items={this.state.items}/>
                <Pagination totalPages={this.state.totalPages} currentPage={this.state.currentPage}
                            onPrev={this.clickPrevHandler} onNext={this.clickNextHandler}
                            onPage={this.clickPageHandler}/>
            </main>
        )
    }
}

export default ItemVisualizationPage;