import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Fournisseurs : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Fournisseurs"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Fournisseurs;