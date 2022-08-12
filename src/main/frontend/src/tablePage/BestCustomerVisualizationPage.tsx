import React, {Component} from "react";
import {UserCard} from "react-ui-cards/dist";
import {fetchPlainText} from "../client/WebServerClient";
import "../style/customer-card.css";

type Props = {}
type State = {
    customerName: string,
    baseApi: string
}

class BestCustomerVisualizationPage extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            customerName: "",
            baseApi: "/api/v1/customer/best-customer"
        };
    }

    async componentDidMount() {
        fetchPlainText(this.state.baseApi)
            .then(response => {
                    response.text()
                        .then(customerName => {
                                console.log("Data: " + customerName);
                                this.setState({customerName: customerName})
                            }
                        )
                }
            )
    }

    render() {
        return (
            <main role="main" className="col-md-9 ml-sm-auto col-lg-10 px-md-4">
                <div className={"form-inline"}>
                    <h1>Лучший клиент за полгода</h1>
                </div>
                {
                    this.state.customerName && (
                        <UserCard
                            href={"https://multicarta.ru/"}
                            header={"https://thumbs.dreamstime.com/b/landscaped-flower-garden-beautiful-blooming-seasonal-flowers-33753005.jpg"}
                            avatar={"https://www.jazz.ru/wp-content/uploads/2020/03/dibango0.jpg"}
                            name={this.state.customerName}
                            positionName={""}
                            stats={[{name: "Особенность", value: "Лучший клиент за полгода"}]}
                            className={"customerCard"}
                        />
                    )}
            </main>
        )
    }
}

export default BestCustomerVisualizationPage;