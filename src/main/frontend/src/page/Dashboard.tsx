import React from 'react'
import {Helmet} from 'react-helmet';
import {DashboardNavbar} from "../navbar/DashboardNavbar";
import {SideNavigationBar} from "../navbar/SideNavBar";

export default class Dashboard extends React.Component {

    render() {
        return (
            <div>
                <Helmet>
                    <title>Бэк-офис | Мультикарта</title>
                </Helmet>
                <DashboardNavbar/>
                <SideNavigationBar/>
            </div>
        )
    }
}