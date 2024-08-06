import Table from "@/app/components/table";
import Nav from "../nav/nav";
import "./section.css";
import {tableElement,tableProps} from "@/app/components/table";

export type sectionProps = {
    title : string;
    tableHeaders : string[];
    tableContent : tableElement[];
}

const Section : React.FC<sectionProps> = ({title,tableHeaders,tableContent}) => {
    return(
            <section className="p-6 flex flex-col justify-between h-full bg-slate-100 rounded-lg">
                <Nav title={title}/>
                <div className="section-main h-4/5 w-full rounded-b-2xl bg-white mx-auto p-8">
                    <Table tableHeaders={tableHeaders} tableContent={tableContent} />
                </div>
            </section>
    );
}
export default Section;