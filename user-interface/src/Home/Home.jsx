import 'bootstrap/dist/css/bootstrap.css'

import React from "react";
import {Card,CardGroup} from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import {Link} from 'react-router-dom';

export default function Home() {
  return (
	<div>
		<h1>E-commerce Portal</h1>
		<CardGroup style={{ width: '75%' }}>
			<Card bg="light" border="info" className="mb-2">
				<Card.Body className="d-flex flex-column">
					<Card.Title>For Business</Card.Title>
					<Card.Text>
						Use our platform to reach customers world-wide, by showcasing your products.
					</Card.Text>
					<Button className="mt-auto p-2" as={Link} to="/seller" variant="info">Add Products</Button>
				</Card.Body>
			</Card>
			<Card bg="light" border="info" className="mb-2">
				<Card.Body className="d-flex flex-column">
					<Card.Title>For Customers</Card.Title>
					<Card.Text>
						Get thousands of products offered by the sellers across the world.
					</Card.Text>
					<Button className="mt-auto p-2" as={Link} to="/products" variant="info">Explore Products</Button>
				</Card.Body>
			</Card>
		</CardGroup>
	</div>
  );
}
