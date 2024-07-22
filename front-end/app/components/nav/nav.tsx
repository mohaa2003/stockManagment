import Link from "next/link";
import Image from "next/image";
import "./nav.css";
import profilPic from "@/public/profil_pic.png";
const Nav : React.FC = () => {
    return(
            <nav className="flex justify-between navbar mx-auto bg-white h-20 w-full rounded-2xl items-center px-8">
                <div className="title">
                    <h2 className="text-lg font-bold">Les Achats</h2>
                </div>
                <div className="personal-info">
                    <Link href={"/personal"}>
                        <Image src={profilPic} width={40} height={40} alt="picture of profile"/>
                    </Link>
                </div>
            </nav>
    );
}
export default Nav;