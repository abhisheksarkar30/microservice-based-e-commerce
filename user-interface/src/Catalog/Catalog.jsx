import 'bootstrap/dist/css/bootstrap.css'

import Alert from 'react-bootstrap/Alert';
import React, { Component } from 'react';

function Product(props) {
	return (
		<Alert productinfo key={props.id} variant="info">
			<Alert.Heading>Product: {props.name}, Category: {props.category}</Alert.Heading>
			<p>{props.desc}</p>
		</Alert>
	);
}

class Catalog extends Component {
	constructor(props) {
		super(props);
		this.state = {
			isLoading: true,
			products: []
		};
	}
	async componentDidMount() {
		const response = await fetch('/api/catalog-server/products');
		const body = await response.json();
		this.setState({ 
			products: body, 
			isLoading: false 
		});
	}
	render() {
		const {products, isLoading} = this.state;
		if (isLoading) {
			return <p>Loading...</p>;
		}
		const productCatalogs = products.map((product) => {
			return (
				<Product id={product.id} name={product.name} category={product.category} desc={product.description}/> 
			);
		});
		return (
			<div>
				<h2>Product List</h2>
				{productCatalogs}
			</div>
		);
	}
}

export default Catalog;