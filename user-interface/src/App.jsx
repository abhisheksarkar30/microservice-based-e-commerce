import React from "react";
import {BrowserRouter as Router,Switch,Route} from 'react-router-dom';
import Catalog from './Catalog/Catalog';
import Home from './Home/Home'

export default function App() {
	return (
		<Router>
			<div>
				<Switch>
					<Route path="/seller">
						<Home />
					</Route>
					<Route path="/products">
						<Catalog />
					</Route>
					<Route path="/">
						<Home />
					</Route>
				</Switch>
			</div>
		</Router>
	);
}