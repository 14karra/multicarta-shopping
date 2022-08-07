import React from 'react';
import {IItem} from "../model/interfaces";
import {ProductCard} from "react-ui-cards/dist";
import '../style/product-card.css';

interface IProps {
    items: IItem[]
}

let ItemsTable = (props: IProps) => (
    <span id={"cardsContainer"}>
        {props.items.map(item => {

            console.log("Unique item: " + JSON.stringify(item));

            return (
                <ProductCard
                    photos={["https://habrastorage.org/getpro/moikrug/uploads/company/100/005/664/4/logo/medium_64946bad323c51fc104937b294ff118e.png"]}
                    price={item.amount.toString() + " ₽"}
                    productName={item.name}
                    description={item.description + "\nДоступно " + item.quantity + " шт"}
                    url={"https://multicarta.ru/"}
                    buttonText={"Купить"}
                    className={"productCard"}
                />
            )
        })}
    </span>
);

export default ItemsTable;