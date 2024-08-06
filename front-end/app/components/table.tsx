export type tableElement = {
    [key:string] : any;
}
export type tableProps = {
    tableHeaders : string[];
    tableContent : tableElement[];
}
const Table : React.FC<tableProps> = ({tableHeaders,tableContent}) => {
    return(
        <table className="main-table">
            <thead>
                <tr>
                    {
                        tableHeaders.map((val : string, ind : number) : React.ReactNode=>
                            (
                                <th key={ind}>
                                    {val}
                                </th>
                            ))
                    }
                </tr>
            </thead>
            <tbody>
                {
                    tableContent &&
                    tableContent.map((val : tableElement , ind : number) : React.ReactNode => (
                        <td key={ind}>
                            {
                                Object.values(val)[ind]
                            }
                        </td>
                    ))
                }
            </tbody>
        </table>
    );
}

export default Table;