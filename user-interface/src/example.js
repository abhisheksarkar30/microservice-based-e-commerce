import 'bootstrap/dist/css/bootstrap.css'
import Alert from 'react-bootstrap/Alert';
import React, { Component } from 'react';

function Example() {
  return (
    <Alert dismissible variant="danger">
      <Alert.Heading>Oh snap! You got an error!</Alert.Heading>
      <p>
        Change this and that and try again.
      </p>
    </Alert>
  )
}

export default Example;