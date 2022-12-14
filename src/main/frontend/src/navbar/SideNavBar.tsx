import React, {Component} from 'react';
import {BrowserRouter, Link, Route} from "react-router-dom";
import "@fortawesome/react-fontawesome";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import ItemVisualizationPage from "../tablePage/ItemVisualizationPage";
import WeeklyPurchasesPage from "../tablePage/WeeklyPurchasesPage";
import ItemPurchasePage from "../tablePage/ItemPurchasePage";
import CustomerVisualizationPage from "../tablePage/CustomerVisualizationPage";
import BestSellerItemVisualizationPage from "../tablePage/BestSellerItemVisualizationPage";
import BestCustomerVisualizationPage from "../tablePage/BestCustomerVisualizationPage";

export class SideNavigationBar extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="container-fluid">
                    <div className="row">
                        <nav id="sidebarMenu" className="col-md-3 col-lg-2 d-md-block bg-light sidebar">
                            <div className="sidebar-sticky pt-3">
                                <ul className="nav flex-column mb-2">
                                    <li className="nav-item btn-group dropright">
                                        <div className="nav-link dropdown-toggle" data-toggle="dropdown"
                                             aria-haspopup="true" aria-expanded="false" role="button">
                                            <FontAwesomeIcon icon={['fas', 'passport']} size="lg"/>
                                            &nbsp;Товары
                                        </div>
                                        <div className="dropdown-menu">
                                            <Link to="/dashboard/items/visualize">
                                                <div className="dropdown-item">Список товаров</div>
                                            </Link>
                                            <Link to="/dashboard/items/perform-purchase">
                                                <div className="dropdown-item">Покупать товар</div>
                                            </Link>
                                            <Link to="/dashboard/items/best-seller">
                                                <div className="dropdown-item">Бестселлер товар</div>
                                            </Link>
                                            <Link to="/dashboard/items/best-seller-for-18-years-old-customers">
                                                <div className="dropdown-item">Бестселлер товар для 18 летных клиентов</div>
                                            </Link>
                                        </div>
                                    </li>
                                    <li className="nav-item btn-group dropright">
                                        <div className="nav-link dropdown-toggle" data-toggle="dropdown"
                                             aria-haspopup="true" aria-expanded="false" role="button">
                                            <FontAwesomeIcon icon={['fas', 'passport']} size="lg"/>
                                            &nbsp;Покупки
                                        </div>
                                        <div className="dropdown-menu">
                                            <Link to="/dashboard/purchases/weekly">
                                                <div className="dropdown-item">Еженедельные покупки</div>
                                            </Link>
                                        </div>
                                    </li>
                                    <li className="nav-item btn-group dropright">
                                        <div className="nav-link dropdown-toggle" data-toggle="dropdown"
                                             aria-haspopup="true" aria-expanded="false" role="button">
                                            <FontAwesomeIcon icon={['fas', 'passport']} size="lg"/>
                                            &nbsp;Клиенты
                                        </div>
                                        <div className="dropdown-menu">
                                            <Link to="/dashboard/customers/visualize">
                                                <div className="dropdown-item">Список клиентов</div>
                                            </Link>
                                            <Link to="/dashboard/customers/best-customer">
                                                <div className="dropdown-item">Лучший клиент за полгода</div>
                                            </Link>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                        <Route strict={true} path="/dashboard/items/visualize" component={ItemVisualizationPage}/>
                        <Route strict={true} path="/dashboard/items/best-seller"
                               render={(props) => <BestSellerItemVisualizationPage {...props}
                                                                                   baseApi={"/api/v1/item/best-seller"}
                                                                                   headerText={"Бестселлер товар"}/>}/>
                        <Route strict={true} path="/dashboard/items/best-seller-for-18-years-old-customers"
                               render={(props) => <BestSellerItemVisualizationPage {...props}
                                                                                   baseApi={"/api/v1/item/best-seller-for-18-years-old"}
                                                                                   headerText={"Бестселлер товар для 18 летных клиентов"}/>}/>
                        <Route strict={true} path="/dashboard/items/perform-purchase" component={ItemPurchasePage}/>
                        <Route strict={true} path="/dashboard/purchases/weekly" component={WeeklyPurchasesPage}/>
                        <Route strict={true} path="/dashboard/customers/visualize"
                               component={CustomerVisualizationPage}/>
                        <Route strict={true} path="/dashboard/customers/best-customer"
                               component={BestCustomerVisualizationPage}/>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}