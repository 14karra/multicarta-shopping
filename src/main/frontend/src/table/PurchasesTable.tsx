import React from 'react';
import {IPurchase} from "../model/interfaces";
import '../style/product-card.css';

interface IProps {
    purchases: IPurchase[]
}

let PurchasesTable = (props: IProps) => (
    <table className="table table-striped table-sm">
        <thead>
        <tr>
            <th className="text" scope="col">Идентификатор покупки</th>
            <th className="text" scope="col">Товар</th>
            <th className="text" scope="col">Количество</th>
            <th className="text" scope="col">Итоговая сумма</th>
            <th className="text" scope="col">Дата оплаты</th>
            <th className="text" scope="col">Покупатель</th>
        </tr>
        </thead>
        <tbody>
        {props.purchases.map(purchase => (
            <tr key={purchase.id}>
                <td>{purchase.id}</td>
                <td>{purchase.item[0].name}</td>
                <td>{purchase.count}</td>
                <td>{purchase.amount}&nbsp;₽</td>
                <td>{purchase.purchaseDate}</td>
                <td>{purchase.customer[0].name}&nbsp;{purchase.customer[0].lastName}</td>
            </tr>
        ))}
        </tbody>
    </table>
);

export default PurchasesTable;