import React, {Component} from "react";
import {ProductCard} from "react-ui-cards/dist";
import {fetchPlainText} from "../client/WebServerClient";
import "../style/product-card.css"

type Props = {
    baseApi: string,
    headerText: string
}
type State = {
    itemName: string
}

class BestSellerItemVisualizationPage extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            itemName: ""
        };
    }

    async componentDidMount() {
        fetchPlainText(this.props.baseApi)
            .then(response => {
                    response.text()
                        .then(itemName => {
                                console.log("Data: " + itemName);
                                this.setState({itemName: itemName})
                            }
                        )
                }
            )
    }

    render() {
        return (
            <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div className={"form-inline"}>
                    <h1>{this.props.headerText}</h1>
                </div>
                {
                    this.state.itemName && (
                        <ProductCard
                            photos={["https://habrastorage.org/getpro/moikrug/uploads/company/100/005/664/4/logo/medium_64946bad323c51fc104937b294ff118e.png"]}
                            price={""}
                            productName={this.state.itemName}
                            description={""}
                            url={"https://multicarta.ru/"}
                            buttonText={"Купить"}
                            className={"productCard"}
                        />
                    )}
            </main>
        )
    }
}

export default BestSellerItemVisualizationPage;