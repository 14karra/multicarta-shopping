import React, {Component} from "react";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
// @ts-ignore
import DatePicker from "react-datepicker";
import {postRequest} from "../client/WebServerClient";
import {extractJsonFromXml, generateUserRequestXml} from "../util/XMLUtil";
import "../style/register.css";
import "react-datepicker/dist/react-datepicker.css";

type Props = {};
type State = {
    username: string,
    password: string,
    name: string,
    lastName: string,
    birthday: Date,
    successful: boolean,
    message: string,
    baseApi: string,
};

class Register extends Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.handleRegister = this.handleRegister.bind(this);
        this.state = {
            username: "",
            password: "",
            name: "",
            lastName: "",
            birthday: new Date(1990, 1, 1),
            successful: false,
            message: "",
            baseApi: "/api/v1/user/register"
        }
    }

    validationSchema() {
        return Yup.object().shape({
            username: Yup.string()
                .test(
                    "len",
                    "Имя пользователя должно содержать от 3 до 20 символов.",
                    (val: any) =>
                        val &&
                        val.toString().length >= 3 &&
                        val.toString().length <= 20
                )
                .required("Это поле обязательно к заполнению!"),
            password: Yup.string()
                .test(
                    "len",
                    "The password must be between 6 and 40 characters.",
                    (val: any) =>
                        val &&
                        val.toString().length >= 6 &&
                        val.toString().length <= 40
                )
                .required("Это поле обязательно к заполнению!"),
            name: Yup.string()
                .test(
                    "len",
                    "Имя должно содержать от 2 до 25 символов.",
                    (val: any) =>
                        val &&
                        val.toString().length >= 2 &&
                        val.toString().length <= 25
                ),
            lastName: Yup.string()
                .test(
                    "len",
                    "Фамилия должна содержать от 2 до 25 символов.",
                    (val: any) =>
                        val &&
                        val.toString().length >= 2 &&
                        val.toString().length <= 25
                ),
            birthday: Yup.string()
                .required("Это поле обязательно к заполнению!")
        });
    }

    handleRegister(formValue: {
        username: string; password: string, name: string,
        lastName: string, birthday: Date
    }) {
        const {username, password, name, lastName, birthday} = formValue;
        this.setState({
            message: "",
            successful: false
        });

        let xmlMessage = generateUserRequestXml(formValue);
        postRequest(this.state.baseApi, xmlMessage)
            .then(response => {
                // Unfortunately, fetch doesn't send (404 error) into the cache itself
                // You have to send it, as I have done below
                if (response.status >= 400) {
                    extractJsonFromXml(response).then(jsonData => {
                        let apiError = (jsonData as any).ApiError;
                        let resMessage = "Status: " + apiError.status + ". " + apiError.message;
                        throw new Error(resMessage);
                    });
                }
                this.setState({
                    message: "Регистрация прошла успешна!",
                    successful: true
                });
            })
            .then(resp => {
                },
                error => {
                    let resMessage = "";
                    if (error.response && error.response.data) {
                        extractJsonFromXml(error.response).then(jsonData => {
                            console.log("Error JSON data:\n" + jsonData);
                            resMessage = ((jsonData as any).message);
                        })
                    } else if (error.message) {
                        console.log("Error message: " + error.message);
                        resMessage = error.message;
                    } else {
                        console.log("Error toString: " + error.toString());
                        resMessage = error.toString();
                    }

                    this.setState({
                        successful: false,
                        message: resMessage
                    });
                }
            );
    }

    render() {
        const {successful, message} = this.state;
        const initialValues = {
            username: "",
            password: "",
            name: "",
            lastName: "",
            birthday: this.state.birthday
        };

        return (
            <div className="col-md-12">
                <div className="card card-container">
                    <img
                        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                        alt="profile-img"
                        className="profile-img-card"
                    />
                    <Formik
                        initialValues={initialValues}
                        validationSchema={this.validationSchema}
                        onSubmit={this.handleRegister}
                    >
                        <Form>
                            {!successful && (
                                <div>
                                    <div className="form-group">
                                        <label htmlFor="username"> Логин </label>
                                        <Field name="username" type="text" className="form-control"/>
                                        <ErrorMessage
                                            name="username"
                                            component="div"
                                            className="alert alert-danger"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="password"> Пароль </label>
                                        <Field
                                            name="password"
                                            type="password"
                                            className="form-control"
                                        />
                                        <ErrorMessage
                                            name="password"
                                            component="div"
                                            className="alert alert-danger"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="name"> Имя </label>
                                        <Field
                                            name="name"
                                            type="text"
                                            className="form-control"
                                        />
                                        <ErrorMessage
                                            name="name"
                                            component="div"
                                            className="alert alert-danger"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="lastName"> Фамилия </label>
                                        <Field
                                            name="lastName"
                                            type="text"
                                            className="form-control"
                                        />
                                        <ErrorMessage
                                            name="lastName"
                                            component="div"
                                            className="alert alert-danger"
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="birthday"> Дата рождения (день/месяц/год) </label>
                                        <DatePicker selected={this.state.birthday}
                                                    onChange={(newDate: Date) => {
                                                        console.log("hahaha");
                                                        this.setState({birthday: newDate})
                                                    }}
                                                    dateFormat="dd/MM/yyyy"
                                                    name="birthday"
                                                    type="date"
                                                    className="form-control"/>
                                        <ErrorMessage
                                            name="birthday"
                                            component="div"
                                            className="alert alert-danger"
                                        />
                                    </div>
                                    <div className="form-group d-flex justify-content-center p-2 login_container">
                                        <button type="submit" className="btn btn-primary">Регистрироваться</button>
                                    </div>
                                    <div className="d-flex justify-content-center ">
                                        Уже есть аккаунт?&nbsp;<a href={"/login"}>войти</a>
                                    </div>
                                </div>
                            )}
                            {message && (
                                <div className="form-group">
                                    <div
                                        className={
                                            successful ? "alert alert-success" : "alert alert-danger"
                                        }
                                        role="alert"
                                    >
                                        {message}
                                    </div>
                                </div>
                            )}
                        </Form>
                    </Formik>
                </div>
            </div>
        );
    }
}

export default Register;