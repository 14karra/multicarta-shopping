import React from 'react'
import {LogoOption} from "../menuOption/LogoOption";
import {DashboardOption} from "../menuOption/DashboardOption";

export const LoginNavbar: React.FunctionComponent = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-sm bg-dark navbar-dark">
                <LogoOption/>
                <div className="navbar-nav ml-auto">
                    <DashboardOption/>
                </div>
            </nav>
        </div>
    )
};