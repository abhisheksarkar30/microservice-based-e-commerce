import React from 'react';
import { useField } from 'formik';
import { TextField } from '@material-ui/core';

export default function TextInput({ label, placeholder, ...props }) {
    const [field, meta] = useField(props);
    return (
        <TextField name="shortName" label={label} error={meta.touched && !!meta.error}
            helperText={(meta.touched && meta.error) ? meta.error+"!" : placeholder} fullWidth
            {...field} {...props} />
    );
}