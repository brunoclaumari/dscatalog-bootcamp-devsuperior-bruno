import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { ReactComponent as ArrowIcon } from '../../../../core/assets/images/arrow.svg';
import './styles.scss';
import '../../../../app.scss';
import ProductPrice from '../../../../core/components/ProductPrice';
import { makeRequest } from '../../../../core/utils/request';
import { Product } from '../../../../core/types/Product';

type ParamsType = {
    productId: string;
}

const ProductDetails = () => {

    const { productId } = useParams<ParamsType>();
    console.log(productId);
    const [prod, setProd]=useState<Product>();
    console.log(prod);

    useEffect(() => {
        console.log(`componente de detalhes iniciado ${productId}`);
        
        makeRequest({ url: `/products/${productId}` })
            .then(response => setProd(response.data));
    }, [productId]);

    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/products" className="product-details-goback" >
                    <ArrowIcon className="icon-goback" />
                    <h1 className="text-goback" >voltar</h1>
                </Link>
                <div className="row">
                    <div className="col-6 pr-5">
                        <div className="product-details-card text-center">                           
                            <img src={prod?.imgUrl} alt={prod?.name} className="product-image" />
                        </div>
                        <h1 className="product-details-name">
                            {prod?.name}
                        </h1>
                        {prod?.price && <ProductPrice price={prod?.price} />}
                    </div>
                    <div className="col-6 pl-5 product-details-card">
                        <h6 className="product-description-title" >
                            Descrição do Produto
                        </h6>
                        <p className="product-description-text" >
                            {prod?.description}
                        </p>
                    </div>
                </div>
            </div>

        </div>
    );

}

export default ProductDetails;