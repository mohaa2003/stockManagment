import Nav from "../nav/nav";
import "./section.css";

const Section : React.FC = () => {
    return(
            <section className="p-6 flex flex-col justify-between h-full bg-slate-100 rounded-lg">
                <Nav/>
                <div className="section-main h-4/5 w-full rounded-b-2xl bg-white mx-auto p-8">
                    <table className="main-table bg-slate-100 w-full rounded-xl">
                        <thead>
                            <tr>
                                <th className="bg-slate-800">qq</th>
                                <th className="bg-slate-800">qq</th>
                                <th className="bg-slate-800">qq</th>
                                <th className="bg-slate-800">qq</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                            </tr>
                            <tr>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                            </tr>
                            <tr>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                            </tr>
                            <tr>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                                <td>qq</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
    );
}
export default Section;