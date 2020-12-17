import React from "react"
import ContentLoader from "react-content-loader"
import { generateList } from "core/utils/list";

type Props = {
  nPerPage:number
}

const ProductCardLoader = ( {nPerPage}:Props ) => {

  const loaderItems = generateList(5);
  //atribuindo numero de itens de acordo com o 'linesPerPage'
  //é mais automático
  //const loaderItems = generateList(nPerPage);

  return (
    <>
      {
        loaderItems.map(item => (
          <ContentLoader
            key={item}
            speed={2}
            width={250}
            height={332}
            viewBox="0 0 250 332"
            backgroundColor="#ecebeb"
            foregroundColor="#d6d2d2"

          >
            {/* <circle cx="31" cy="31" r="15" /> 
        <rect x="58" y="18" rx="2" ry="2" width="140" height="10" /> 
        <rect x="58" y="34" rx="2" ry="2" width="140" height="10" />  */}
            <rect x="0" y="0" rx="10" ry="10" width="250" height="332" />
          </ContentLoader>
        ))
      }
    </>
  )
}

export default ProductCardLoader

