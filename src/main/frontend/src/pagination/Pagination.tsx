import React, {useEffect, useState} from "react";
import arrowRight from "../assets/arrow-right.svg";
import arrowLeft from "../assets/arrow-left.svg";

interface IPaginationProps {
    totalPages: number,
    currentPage: number,
    onPrev(): void,
    onNext(): void,
    onPage(pageNumber: number): void
}

let Pagination = (props: IPaginationProps) => {
    const [pages, setPages] = useState<number[]>([]);

    useEffect(() => {
        for (let i = 0; i < props.totalPages; i++) {
            pages.push(i + 1);
        }

        setPages([...pages]);
    }, [props.totalPages]);

    return (
        <nav aria-label="Page navigation example">
            <ul className="pagination">
                <li className="page-item">
                    <button onClick={() => props.onPrev()} className="page-link">
                        <img src={arrowLeft} alt=""/>&nbsp; Предыдущий
                    </button>
                </li>
                {pages.map(item => (
                    <li className="page-item" key="item">
                        <button onClick={() => props.onPage(item - 1)}
                                className={((item - 1 === props.currentPage) ? "page-link current-page" : "page-link")}>{item}</button>
                    </li>)
                )}
                <li className="page-item">
                    <button onClick={() => props.onNext()} className="page-link">Следующий&nbsp;
                        <img src={arrowRight} alt=""/>
                    </button>
                </li>
            </ul>
        </nav>
    )
};

export default Pagination;