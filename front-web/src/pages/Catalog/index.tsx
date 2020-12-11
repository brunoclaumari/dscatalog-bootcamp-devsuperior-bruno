import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { makeRequest } from '../../core/utils/request';
import ProductCard from './components/ProductCard';
import './styles.scss';
import { ProductsResponse } from '../../core/types/Product';

const Catalog = () => {

    //quando a lista de produtos estiver disponivel,
    //popular um estado do componente, e listar os produtos dinamicamente
    const [productsResponse, setProductsResponse] = useState<ProductsResponse>();

    console.log(productsResponse);

    //quando o componente iniciar buscar a lista de produtos
    useEffect(() => {
        //no arquivo 'package.json' a porta no 'proxy' está setada para o 8080, 
        //onde está o backend, mas aqui no fetch é direcionado para a 3000   
        const params = {
            page: 0,
            linesPerPage: 12
        }

        makeRequest({ url: '/products', params })
            .then(response => setProductsResponse(response.data));
    }, []);

    return (
        <div className="catalog-container" >
            <h1 className='catalog-title' >
                Catálogo de produtos
            </h1>
            <div className="catalog-products" >
                {productsResponse?.content.map(prod => (
                    <Link to={`products/${prod.id}`} key={prod.id}>
                        <ProductCard product={prod}/>
                    </Link>
                ))}

            </div>
        </div>
    )
};


export default Catalog;


        //-fetch tem limitações
        //muito verboso
        //não tem suporte nativo de uploads de arquivos
        // não tem suporte nativo para enviar query strings

/* fetch(url)
.then(resp => resp.json())
.then(resp=>console.log(resp));  */