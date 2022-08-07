import React from 'react'
import '../style/loginBox.css'
import {BrowserRouter, Route} from "react-router-dom";
import AlertMessage from "../util/AlertMessage";

export const LoginBox: React.FunctionComponent = () => {
    return (
        <div className="container h-75">
            <div className="d-flex justify-content-center h-100">
                <div className="user_card ">
                    <div className="d-flex justify-content-center form_container">
                        <form method="post" action="/login">
                            <div className="input-group mb-3">
                                <div className="input-group-append">
                                    <span className="input-group-text"><i className="fas fa-user"/></span>
                                </div>
                                <input type="text" id="username" name="username" className="form-control input_user"
                                       placeholder="имя пользователя"/>
                            </div>
                            <div className="input-group mb-2">
                                <div className="input-group-append">
                                    <span className="input-group-text"><i className="fas fa-key"/></span>
                                </div>
                                <input type="password" id="password" name="password" className="form-control input_pass"
                                       placeholder="пароль"/>
                            </div>
                            <div className="form-check">
                                <input type="checkbox" className="form-check-input" id="remember-me" name="remember-me"/>
                                <label className="form-check-label" htmlFor="remember-me">Запомнить меня</label>
                            </div>
                            <div className="d-flex justify-content-center mt-3 p-3 login_container">
                                <button type="submit" className="btn btn-primary">Войти</button>
                            </div>
                            <BrowserRouter>
                                <Route path="/login/login-error"
                                       render={(props) =>
                                           <AlertMessage {...props} msg={`Неправильное имя пользоваеля или пароль!`}
                                                         classStyle="alert-danger"/>}
                                />
                            </BrowserRouter>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
};