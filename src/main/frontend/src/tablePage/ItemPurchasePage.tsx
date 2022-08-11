import React, {Component} from "react";
import {IItem} from "../model/interfaces";
import {extractJsonFromXml, generatePurchaseRequestXml} from "../util/XMLUtil";
import fetchXml, {postRequest} from "../client/WebServerClient";
import FixRequiredSelect from "../util/FixRequiredSelect";
import BaseSelect from "react-select";

type Props = {};
type State = {
    items: IItem[],

    itemId: number,
    quantity: number,

    itemValid: boolean,
    quantityValid: boolean,

    baseApi: string,
    purchaseBaseApi: string,
    successful: boolean,
    message: string
}

const Select = (props: any) => (
    <FixRequiredSelect
        {...props}
        SelectComponent={BaseSelect}
        options={props.options}
    />
);


class ItemPurchasePage extends Component<{}, State> {

    constructor(props: Props) {
        super(props);
        this.handlePurchase = this.handlePurchase.bind(this);
        this.onItemChange = this.onItemChange.bind(this);
        this.onQuantityChange = this.onQuantityChange.bind(this);
        this.state = {
            items: [],
            itemId: -1,
            quantity: 1,

            itemValid: false,
            quantityValid: false,

            baseApi: "/api/v1/item",
            purchaseBaseApi: "/api/v1/purchase",

            successful: false,
            message: ""
        };
    }

    async componentDidMount() {
        fetchXml(this.state.baseApi + "/available").then(response =>
            extractJsonFromXml(response).then(jsonData => {
                    let items = (jsonData as any).Items.Item;
                    console.log("Data: " + JSON.stringify(items));
                    this.setState({items: items});
                }
            )
        )
    }

    onItemChange(e: any) {
        let val = e.id;
        let valid = this.validateExternalSelection(val);
        this.setState({itemId: val, itemValid: valid});
    }

    onQuantityChange(e: any) {
        const quantity = e.target.value;
        const quantityValid = (quantity === undefined) ? false : (quantity > 0);

        this.setState({
            quantity: quantity, quantityValid: quantityValid
        });
    }

    validateExternalSelection(dataId: any) {
        return (dataId === undefined) ? false : (dataId.toString().length >= 0);
    }

    handlePurchase(e: any) {
        e.preventDefault();
        console.log("Handling purchase...");
        console.log("itemId: " + this.state.itemId + "\nquantity: " + this.state.quantity);
        this.setState({
            message: "",
            successful: false
        });

        let xmlMessage = generatePurchaseRequestXml(this.state.itemId, this.state.quantity);
        postRequest(this.state.purchaseBaseApi, xmlMessage)
            .then(response => {
                if (response.status >= 400) {
                    throw new Error("Server responds with error!");
                }
                this.setState({
                    message: "Покупка прошла успешна!",
                    successful: true
                });
            })
            .then(resp => {
                },
                error => {
                    let resMessage = "";
                    if (error.response && error.response.data) {
                        extractJsonFromXml(error.response).then(jsonData => {
                            console.log("Error JSON data:\n" + jsonData);
                            resMessage = ((jsonData as any).message);
                        })
                    } else if (error.message) {
                        console.log("Error message: " + error.message);
                        resMessage = error.message;
                    } else {
                        console.log("Error toString: " + error.toString());
                        resMessage = error.toString();
                    }

                    this.setState({
                        successful: false,
                        message: resMessage
                    });
                }
            );
    }

    render() {
        return (
            <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div className="container h-100 d-flex justify-content-center">
                    <h1>Покупка товара</h1>
                </div>
                <form className={"w-75"} onSubmit={this.handlePurchase}>
                    {!this.state.successful && (
                        <div className={"col-sm-10"}>
                            <div className="form-group input-group-sm mb-3 row">
                                <label htmlFor="itemId" className="form-label text-right col-sm-3">Товар<i
                                    style={{color: "red"}}>*</i></label>
                                <Select ref={"itemId"}
                                        id={"itemId"}
                                        name={"itemId"}
                                        className={"form-control col-sm-6"}
                                        getOptionValue={(option: IItem) => option.id}
                                        getOptionLabel={(option: IItem) => option.name}
                                        options={this.state.items}
                                        value={this.state.items.find((item: IItem) => item.id === this.state.itemId)}
                                        onChange={this.onItemChange}
                                        isSearchable
                                        isDisabled={false}
                                        required>
                                </Select>
                            </div>
                            <div className="form-group input-group-sm mb-3 row">
                                <label htmlFor="quantity" className="form-label text-right col-sm-3">Количетво<i
                                    style={{color: "red"}}>*</i></label>
                                <div className="col col-lg-6">
                                    <input name="quantity" type="number" className="form-control"
                                           onChange={this.onQuantityChange}
                                    />
                                </div>
                            </div>
                            <div className="form-group d-flex justify-content-center p-2 login_container">
                                <button type="submit" className="btn btn-primary"
                                        disabled={!this.state.itemValid || !this.state.quantityValid}
                                >Купить
                                </button>
                            </div>
                        </div>)}
                    {this.state.message && (
                        <div className="form-group">
                            <div
                                className={
                                    this.state.successful ? "alert alert-success" : "alert alert-danger"
                                }
                                role="alert"
                            >
                                {this.state.message}
                            </div>
                        </div>
                    )}
                </form>
            </main>
        )
    }
}

export default ItemPurchasePage;