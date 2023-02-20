import styles from '../../styles/Components/Auth/register.module.scss';
import { Button, TextField } from '@mui/material';
import React from 'react';

export function Register() {
	return (
		<form className={ styles.registerForm }>
			<TextField
				id="email"
				label="Email address"
				variant="outlined"
				type="email"
				error={false}
				helperText={false}
				margin={'normal'}
			/>

			<TextField
				id="password"
				label="Password"
				variant="outlined"
				type="password"
				error={false}
				helperText={false}
				margin={'normal'}
			/>

			<TextField
				id="password"
				label="Repeat Password"
				variant="outlined"
				type="password"
				error={false}
				helperText={false}
				margin={'normal'}
			/>

			<Button type="submit" variant="contained" disabled={true}>Sign up</Button>
		</form>
	);
}
