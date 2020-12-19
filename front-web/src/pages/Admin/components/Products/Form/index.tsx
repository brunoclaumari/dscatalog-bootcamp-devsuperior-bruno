import React from 'react';
import BaseForm from '../../BaseForm';
import './styles.scss';


const Form = () => {
    return (
        <BaseForm title="CADASTRAR UM PRODUto" >
            <div className="row">
                <div className="col-6">
                    <input type="text" className="form-control" placeholder="Nome de produto"/>
                </div>
                <div className="col-6">

                </div>
            </div>

        </BaseForm>
    )

}
export default Form;