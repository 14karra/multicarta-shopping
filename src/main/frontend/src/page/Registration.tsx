import * as React from "react";
import {Helmet} from 'react-helmet';
import '../style/loginPage.css';
import {LoginNavbar} from "../navbar/LoginNavbar";
import {LoginBox} from "../subcomponent/LoginBox";
import {Footer} from "../subcomponent/Footer";
import Register from "../subcomponent/Register";

export const Registration: React.FunctionComponent = () => (
    <div id="registrationPageContainer">
        <Helmet>
            <title>регистрация | Мультикарта</title>
        </Helmet>
        <LoginNavbar/>
        <Register/>
        <Footer/>
    </div>
);