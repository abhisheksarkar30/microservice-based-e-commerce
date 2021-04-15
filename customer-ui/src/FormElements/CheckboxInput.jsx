import React from 'react';
import { useField, useFormikContext } from 'formik';
import { FormControlLabel } from '@material-ui/core';
import Checkbox from '@material-ui/core/Checkbox';

export default function CheckboxInput({ label, changeHandler, ...props }) {
    const {setFieldValue} = useFormikContext();
    const [field] = useField({ ...props, type: 'checkbox' });

    const handleChange = (e) => {
        let fieldName = props.id || props.name;
        if(changeHandler) {
            changeHandler(e); 
        }
        setFieldValue(fieldName, e.target.checked);
    }
    return (
        <FormControlLabel
            label={label}
            control={
                <Checkbox {...field} {...props} onChange={handleChange} />
            }
        />
    );
}