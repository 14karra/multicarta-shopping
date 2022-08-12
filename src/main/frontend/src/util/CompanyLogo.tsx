import React from 'react'
import MulticartaLogo from "../assets/logo.png";
import '../style/navbar.css';

export const CompanyLogo: React.FunctionComponent = () => {
    return (
        <img src={MulticartaLogo} id='multicarta_logo' alt="Мультикарта logo"/>
    )
};