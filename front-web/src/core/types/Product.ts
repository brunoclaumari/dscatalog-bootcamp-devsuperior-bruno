
export type ProductsResponse = {
    content: Product[],
    totalPages: number
    //pageable:Pageable
}

export type Product = {
    id:number,
    name:string,
    description:string,
    price:number,
    imgUrl:string,
    date:string,
    categories: Category[]   

}

export type Category = {
    id:number,
    name:string
}

export type Pageable = {
    pageNumber:number
}

/*
"pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageSize": 12,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    }
*/