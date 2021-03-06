import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import './styles.scss';

const Navbar = () => (
    <nav className="row bg-primary main-nav" >
        <div className="col-2">
            <Link to="/" className="nav-logo-text">
                <h4>Ds Catalog</h4>
            </Link>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <NavLink to="/" exact>
                    <li>HOME</li>
                </NavLink>
                <NavLink to="/products" >
                    <li>CATÁLOGO</li>
                </NavLink>
                <NavLink to="/admin" >
                    <li>ADMIN</li>
                </NavLink>
            </ul>
        </div>
    </nav>);

export default Navbar;
