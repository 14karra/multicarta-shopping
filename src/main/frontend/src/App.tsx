import * as React from "react";
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom';
import './App.css';
import Helmet from "react-helmet";
import Dashboard from "./page/Dashboard";
import {NotFound} from "./page/NotFound";
import MulticardSmallLogo from './assets/small_logo.png';
import {Login} from "./page/Login";
import {library} from '@fortawesome/fontawesome-svg-core';
import {faPassport, fas} from '@fortawesome/free-solid-svg-icons';

import {fab, faGithub, faTelegram, faTwitter} from "@fortawesome/free-brands-svg-icons"
import {Registration} from "./page/Registration";

library.add(fas, fab, faTelegram, faGithub, faTwitter, faPassport);

function App() {
    return (
        <Router>
            <Helmet>
                <meta charSet="utf-8"/>
                <title>Главная | Мультикарта</title>
                <link rel="shortcut icon" href={MulticardSmallLogo}/>
                <meta httpEquiv="content-type" content="text/html; charset=utf-8"/>
                <meta name="keywords" content="Мультикарта Shopping"/>
                <meta name="description" content="Front-end application for Мультикарта Shopping"/>
                <meta name="author" content="Djambong Hank"/>
            </Helmet>
            <Switch>
                <Redirect exact path="/" to="/login"/>
                <Route exact path="/login" component={Login}/>
                <Route path="/login/**" component={Login}/>
                <Route path="/register" component={Registration}/>
                <Route path="/register/**" component={Registration}/>
                <Route exact path="/dashboard" component={Dashboard}/>
                <Route path="/dashboard/**" component={Dashboard}/>
                <Route exact path="/404" component={NotFound}/>
                <Redirect to="/404"/>
            </Switch>
        </Router>
    );
}

export default App;
