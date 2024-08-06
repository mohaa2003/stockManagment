import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Clients : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Clients"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Clients;