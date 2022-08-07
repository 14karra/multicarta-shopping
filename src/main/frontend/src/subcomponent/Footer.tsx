import React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import ReactTooltip from "react-tooltip";

export const Footer: React.FunctionComponent = () => {
    return (
        <footer className="text-center fixed-bottom" style={{backgroundColor: "#f48564", flexShrink: 0}}>
            <div className="container p-3">
                <section className="row d-flex justify-content-center">
                    <div className="text-center col-4 border-right border-dark ">
                        &copy; 2022 &nbsp;
                        <a className="text-dark" href="https://github.com/14karra">Djambong Hank</a>
                    </div>
                    <span className="col-3 border-right border-dark">
                        <a className="m-1" href="mailto:hankdjambong@gmail.com">
                            <FontAwesomeIcon icon={['fas', 'envelope']} color={"#4285F4"} size={'2x'}
                                             className="ml-auto"/></a>
                    </span>
                    <form action="" className="col-3 inline-flex form-inline">
                        <div className="form-group mx-sm-3">
                            <div className="form-outline col-6">
                                <input type="email" id="form5Example2" className="form-control"
                                       placeholder="Электронная почта"/>
                            </div>
                            <i data-tip data-for='newsLetterSubscription' className="col-4 ml-4">
                                <button type="submit" className="btn btn-primary">Рассылка</button>
                                <ReactTooltip id='newsLetterSubscription' type='info'>
                                    <span>Подписаться на нашу новостную рассылку</span>
                                </ReactTooltip>
                            </i>
                        </div>
                    </form>
                </section>
            </div>
        </footer>
    )
};