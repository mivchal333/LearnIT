import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import HomePage from "./components/main/HomePage";

const App = () => {


    return (

        <Router>
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/about">About</Link>
                        </li>
                        <li>
                            <Link to="/users">Users</Link>
                        </li>
                    </ul>
                </nav>
                <Switch>
                    <Route path="/">
                        <HomePage/>
                    </Route>
                </Switch>
            </div>
        </Router>
    );
}

export default App;
