import React from 'react';
import ReactDOM from 'react-dom';
import './main/index.css';
import App from './main/App';
import store from "./main/store/store";
import {Provider} from "react-redux";

ReactDOM.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
);