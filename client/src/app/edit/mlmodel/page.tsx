'use client'
import MLModelEditForm from "@/app/components/modelEditForm";
import { url } from "@/app/consts";
import { useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ModelEdit() {
    const searchParams = useSearchParams();

    const [model, setModel] = useState(null);

    useEffect(() => {
        fetch(`${url}/mlmodel?${searchParams}`)
            .then(res => res.json())
            .then(json => setModel(json));
    }, []);

    return model
        ?
        <MLModelEditForm initial={model} />
        :
        <p>loading...</p>
}