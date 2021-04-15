import React from 'react';
import {BrowserRouter as Router,Switch,Route} from 'react-router-dom';
import Login from './Login/Login'

export default function App() {
	return (
		<Router>
			<div>
				<Switch>
					<Route path="/login">
						<Login />
					</Route>
					<Route path="/">
						<Login />
					</Route>
				</Switch>
			</div>
		</Router>
	);
}