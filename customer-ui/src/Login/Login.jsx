import React, { useState } from 'react';
import { Formik, Form } from 'formik';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Grid from '@material-ui/core/Grid';
import { Button, makeStyles, Typography } from '@material-ui/core';
import TextInput from 'FormElements/TextInput';
import InputAdornment from '@material-ui/core/InputAdornment';
import IconButton from '@material-ui/core/IconButton';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import CheckboxInput from 'FormElements/CheckboxInput';
import { postData } from '../util'
import * as Yup from 'yup';

const useStyles = makeStyles({
    root: {
        minWidth: '10%',
        maxWidth: '35%',
        margin: 'auto',
        marginTop: '10%',
        padding: 'auto',
        flexGrow: 1
    },
    button: {
        backgroundColor: '#64B5F6',
        '&:hover': {
           background: '#2196F3',
        },
    }
});

export default function Login() {
    const [ showPassword, setShowPassword ] = useState(false);
    const classes = useStyles();

    return (
        <Card variant="outlined" className={classes.root}>
            <CardContent>
                <Formik
					initialValues={{
						username: '',
                        password: '',
                        stayLoggedIn: false
					}}
                    validationSchema={Yup.object({
                        username: Yup.string().required('Required'),
                        password: Yup.string().required('Required')
                    })}
					onSubmit={async (values, { setSubmitting }) => {
						console.log(values);
                        postData('/api/authn-server/authenticate', values)
							.then(setSubmitting(false)).then(data => { handleResponse(data); });
					}}
				>
                    <Form>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <Typography variant='h4' color="textPrimary" align="center">Sign In</Typography>
                            </Grid>
                            <Grid item xs={12}>
                                <TextInput name="username" required label="Enter Your User Id"/>
                            </Grid>
                            <Grid item xs={12}>
                                <TextInput name="password" required 
                                type={showPassword ? "text" : "password"} label="Enter Your Password"
                                InputProps={{
                                    endAdornment:
                                        <InputAdornment position="end">
                                            <IconButton onClick={() => setShowPassword(!showPassword)}>
                                                {showPassword ? <Visibility /> : <VisibilityOff />}
                                            </IconButton>
                                        </InputAdornment>
                                }}/>
                            </Grid>
                            <Grid item xs={12}>
                                <CheckboxInput name="stayLoggedIn" label="Keep me logged in" color="primary"/>
                            </Grid>
                            <br/>
                            <Grid item xs={12}>
                                <Button className={classes.button} variant="contained" color="primary" 
                                type="submit" size="large" fullWidth>Submit</Button>
                            </Grid>
                        </Grid>
                    </Form>
                </Formik>
            </CardContent>
        </Card>
    );

    function handleResponse(response) {
		// parse JSON response into native JavaScript objects
		response.json().then(responseData => {
			console.log(responseData);
			if (!response.ok) {
				
			} else {
				
			}
		});
	}
}