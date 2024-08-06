import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Transactions : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Transactions"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Transactions;