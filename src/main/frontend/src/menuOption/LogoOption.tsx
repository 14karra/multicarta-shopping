import React from 'react'
import {CompanyLogo} from "../util/CompanyLogo";
import {NavLink} from "react-router-dom";

export const LogoOption: React.FunctionComponent = () => {
    return (
        <div>
            <NavLink to="/" className="navbar-brand img-fluid">
                <CompanyLogo/>
            </NavLink>
        </div>
    )
};