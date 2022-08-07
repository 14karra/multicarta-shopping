import React, {Component} from 'react'
import {LogoOption} from "../menuOption/LogoOption";
import {LogoutOption} from "../menuOption/LogoutOption";

export class DashboardNavbar extends Component {

    render() {
        return (
            <div>
                <nav className="navbar topnav navbar-expand-sm bg-dark navbar-dark">
                    <LogoOption/>
                    <div className="navbar-nav ml-auto">
                        <LogoutOption/>
                    </div>
                </nav>
            </div>
        )
    }
}