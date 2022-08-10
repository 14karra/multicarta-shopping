import React, {Component} from 'react';
import {BrowserRouter, Link, Route} from "react-router-dom";
import "@fortawesome/react-fontawesome";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import ItemVisualizationPage from "../tablePage/ItemVisualizationPage";
import WeeklyPurchasesPage from "../tablePage/WeeklyPurchasesPage";

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
                                            <Link to="/dashboard/items">
                                                <div className="dropdown-item">Список товаров</div>
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
                                </ul>
                            </div>
                        </nav>
                        <Route path="/dashboard/items" component={ItemVisualizationPage}/>
                        <Route path="/dashboard/purchases/weekly" component={WeeklyPurchasesPage}/>
                    </div>
                </div>
            </BrowserRouter>
        );
    }
}