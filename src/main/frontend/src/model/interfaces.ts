export interface IItem {
    id: number,
    name: string
    description: string,
    amount: number,
    quantity: number,
    insertionDate: string
}

export interface ICustomer {
    id: string,
    name: string,
    lastName: string
}

export interface IPurchase {
    id: string,
    count: number,
    amount: number,
    purchaseDate: string,
    item: IItem[],
    customer: ICustomer[]
}
