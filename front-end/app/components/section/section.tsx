import Nav from "../nav/nav";
import "./section.css";

const Section : React.FC = () => {
    return(
            <section className="p-6 flex flex-col justify-between h-full bg-slate-100 rounded-lg">
                <Nav/>
                <div className="section-main h-4/5 w-full rounded-b-2xl bg-white mx-auto p-8">
                    <table className="bg-slate-200 w-full rounded-xl">
                        <thead>
                            <tr>
                                <th>qq</th>
                                <th>qq</th>
                                <th>qq</th>
                                <th>qq</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>qq</th>
                                <th>qq</th>
                                <th>qq</th>
                                <th>qq</th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
    );
}
export default Section;