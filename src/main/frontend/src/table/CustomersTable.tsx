import React from 'react';
import {ICustomer} from "../model/interfaces";
import {UserCard} from "react-ui-cards/dist";
import '../style/customer-card.css';

interface IProps {
    customers: ICustomer[]
}

let CustomersTable = (props: IProps) => (
    <span id={"cardsContainer"}>
        {props.customers.map(customer => {

            console.log("Unique customer: " + JSON.stringify(customer));

            return (
                <UserCard
                    href={"https://multicarta.ru/"}
                    header={"https://thumbs.dreamstime.com/b/landscaped-flower-garden-beautiful-blooming-seasonal-flowers-33753005.jpg"}
                    avatar={"https://www.jazz.ru/wp-content/uploads/2020/03/dibango0.jpg"}
                    name={customer.name + " " + customer.lastName}
                    positionName={customer.username}
                    stats={[{name: "дата рождения", value: customer.birthday}]}
                    className={"customerCard"}
                />
            )
        })}
    </span>
);

export default CustomersTable;