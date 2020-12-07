import React from 'react';
import './styles.scss';

const Navbar = () => (
    <nav className="row bg-primary main-nav" >
        <div className="col-2">
            <a href="/" className="nav-logo-text">
                <h4>Ds Catalog</h4>
            </a>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <a href="link" className="active">
                    <li>HOME</li>
                    </a>
                <a href="link">
                    <li>CATÁLOGO</li>
                </a>
                <a href="link">
                    <li>ADMIN</li>
                </a>
            </ul>
        </div>
    </nav>);

export default Navbar;
