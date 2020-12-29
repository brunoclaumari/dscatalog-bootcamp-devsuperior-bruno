import { makeRequest } from 'core/utils/request';
import React, { useState } from 'react';
import BaseForm from '../../BaseForm';
import './styles.scss';

/*
        "id": 1,
            "name": "The Lord of the Rings",
            "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "price": 90.5,
            "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
            "date": "2020-07-13T20:50:07.123450Z",
            "categories": []
        */

type FormState = {
    name: string,
    price: string,
    category: string,
    description: string
}

//Adicionando varios tipos de elementos com eventos para usar no onChange
type FormEvent = React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>;

const Form = () => {
    //usando 'state' e iniciando como um objeto vazio "{}"
    const [formData, setFormData] = useState<FormState>({
        name: '',
        price: '',
        category: '1',
        description: '',
    });
    
    
    const handleOnChange = (event: FormEvent ) => {
        const attribute = event.target.name;
        const value = event.target.value;

        //console.log({attribute, value});
        setFormData(data => ({
            ...data, [attribute]: value
        }));
    }


    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const payload = {
            ...formData,
            imgUrl: 'https://tudosobreprodutos.com.br/img/console-xbox-one-x-1tb-4k-project-scorpio-edition-controle-sem-fio_1_.png',
            categories: [{ id: formData.category }]
        }

        makeRequest({ method: 'POST', url: '/products', data: payload })
            .then(() => {
                setFormData({ name: '', category: '', price: '', description:'' })
            })
        //console.log(payload);
    }

    return (

        <form onSubmit={handleSubmit} >
            <BaseForm title="CADASTRAR UM produto" >
                <div className="row">
                    <div className="col-6">
                        <input
                            value={formData.name}
                            name="name"
                            type="text"
                            className="form-control mb-5"
                            placeholder="Nome de produto"
                            onChange={handleOnChange}
                        />
                        <select
                            value={formData.category}
                            name="category"
                            className="form-control mb-5"
                            onChange={handleOnChange}
                        >
                            <option value="1">Livros</option>
                            <option value="2">Eletrônicos</option>
                            <option value="3">Computadores</option>
                        </select>

                        <input
                            value={formData.price}
                            name="price"
                            type="text"
                            className="form-control mb-5"
                            placeholder="Preço do produto"
                            onChange={handleOnChange}
                        />
                    </div>
                    <div className="col-6">
                        <textarea 
                        name="description"  
                        value={formData.description}
                        onChange={handleOnChange}
                        cols={30} 
                        rows={10}   
                        className="form-control" 
                        placeholder="Descrição"                    
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    )

}
export default Form;