import React, {Component} from 'react';
import {BrowserRouter, Link, Route} from "react-router-dom";
import "@fortawesome/react-fontawesome";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import ItemVisualizationPage from "../tablePage/ItemVisualizationPage";
import WeeklyPurchasesPage from "../tablePage/WeeklyPurchasesPage";
import ItemPurchasePage from "../tablePage/ItemPurchasePage";
import CustomerVisualizationPage from "../tablePage/CustomerVisualizationPage";

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
                                                <div className="dropdown-item">Недельные покупки</div>
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
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                        <Route strict={true} path="/dashboard/items/visualize" component={ItemVisualizationPage}/>
                        <Route strict={true} path="/dashboard/items/perform-purchase" component={ItemPurchasePage}/>
                        <Route strict={true} path="/dashboard/purchases/weekly" component={WeeklyPurchasesPage}/>
                        <Route strict={true} path="/dashboard/customers/visualize" component={CustomerVisualizationPage}/>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}