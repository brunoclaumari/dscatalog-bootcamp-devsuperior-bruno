import React from 'react';
import { useHistory } from 'react-router-dom';

const List =  () =>{
    //o 'useHistory' é uma função do react que mantém uma pilha de rotas, para 
    //poder ser usadas
    const history = useHistory();

    const handleCreate = ()=> {
        history.push('/admin/products/create');
    }

    return (
        <div className="admin-products-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate} >
                ADICIONAR
            </button>
        </div>

    )
}

export default List;