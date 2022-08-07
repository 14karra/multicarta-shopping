import * as React from "react";
import {Helmet} from 'react-helmet';
import '../style/loginPage.css';
import {LoginNavbar} from "../navbar/LoginNavbar";
import {LoginBox} from "../subcomponent/LoginBox";
import {Footer} from "../subcomponent/Footer";

export const Login: React.FunctionComponent = () => (
    <div id="loginPageContainer">
        <Helmet>
            <title>Войти | Мультикарта</title>
        </Helmet>
        <LoginNavbar/>
        <LoginBox />
        <Footer/>
    </div>
);