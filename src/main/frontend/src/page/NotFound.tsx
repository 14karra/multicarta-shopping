import * as React from "react";
import {Link} from 'react-router-dom';
import {Helmet} from 'react-helmet';
import PageNotFound from '../assets/PageNotFound.png';

export const NotFound: React.FunctionComponent = () => (
    <div style={{textAlign: "center"}}>
        <Helmet>
            <title>Страница не найдена | Мультикарта</title>
        </Helmet>
        <img src={PageNotFound} alt={'Page Not found'}/>
        <h3>
            <Link to="/">Вернуться на главную страницу</Link>
        </h3>
    </div>
);