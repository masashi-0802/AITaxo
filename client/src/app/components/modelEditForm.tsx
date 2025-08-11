'use client'
import React, { useEffect, useMemo, useRef, useState } from "react";
import { MLModel } from "../types";

export type MLModelEditFormProps = {
  /** When provided, the form will edit this model; otherwise it creates a new one. */
  initial?: MLModel;
  /** Called after successful save */
  onSaved?: (saved: MLModel) => void;
  /** Called after successful delete */
  onDeleted?: (deletedId: number) => void;
  /**
   * Base URL for the API. Defaults to "/mlmodels".
   * Expected endpoints:
   *  - POST   /mlmodels             (create)
   *  - PUT    /mlmodels/{id}        (update)
   *  - DELETE /mlmodels/{id}        (delete)
   */
  apiBaseUrl?: string;
};

const inputBase =
  "w-full rounded-xl border border-gray-300 bg-white px-4 py-2 text-gray-900 placeholder-gray-400 shadow-sm focus:outline-none focus:ring-4 focus:ring-indigo-100 focus:border-indigo-500";

const labelBase = "block text-sm font-medium text-gray-700 mb-1";

const buttonBase =
  "inline-flex items-center justify-center rounded-2xl px-4 py-2 text-sm font-semibold shadow-sm transition disabled:opacity-50 disabled:cursor-not-allowed";

const primaryBtn = `${buttonBase} bg-indigo-600 text-white hover:bg-indigo-700 focus:ring-4 focus:ring-indigo-200`;
const secondaryBtn = `${buttonBase} bg-white text-gray-900 border border-gray-300 hover:bg-gray-50`;
const dangerBtn = `${buttonBase} bg-rose-600 text-white hover:bg-rose-700 focus:ring-4 focus:ring-rose-200`;

export default function MLModelEditForm({
  initial,
  onSaved,
  onDeleted,
  apiBaseUrl = "/mlmodels",
}: MLModelEditFormProps) {
  const [name, setName] = useState(initial?.name ?? "");
  const [fullName, setFullName] = useState(initial?.fullName ?? "");
  const [explain, setExplain] = useState(initial?.explain ?? "");

  const [submitting, setSubmitting] = useState(false);
  const [deleting, setDeleting] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const isEdit = useMemo(() => typeof initial?.id === "number", [initial?.id]);

  // Textarea autosize simple helper
  const explainRef = useRef<HTMLTextAreaElement | null>(null);
  useEffect(() => {
    if (explainRef.current) {
      explainRef.current.style.height = "0px";
      const scrollH = explainRef.current.scrollHeight;
      explainRef.current.style.height = Math.max(scrollH, 120) + "px";
    }
  }, [explain]);

  // Reset success message after a moment
  useEffect(() => {
    if (!success) return;
    const t = setTimeout(() => setSuccess(null), 2500);
    return () => clearTimeout(t);
  }, [success]);

  const validate = (): string | null => {
    if (!name.trim()) return "Name is required.";
    if (name.length > 255) return "Name must be at most 255 characters.";
    if (fullName && fullName.length > 255)
      return "Full name must be at most 255 characters.";
    if (explain && explain.length > 1000)
      return "Explain must be at most 1000 characters.";
    return null;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    const v = validate();
    if (v) {
      setError(v);
      return;
    }
    setSubmitting(true);
    try {
      const payload: MLModel = {
        id: initial ? initial.id : -1,
        name: name.trim(),
        fullName: fullName?.trim(),
        explain: explain?.trim(),
        tags: [],
        theses: [],
        presentations: []
      };

      const url = isEdit ? `${apiBaseUrl}/${initial!.id}` : apiBaseUrl;
      const method = isEdit ? "PUT" : "POST";

      const res = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json", Accept: "application/json" },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || `${method} ${url} failed with ${res.status}`);
      }

      const saved: MLModel = await res.json().catch(() => payload);
      setSuccess(isEdit ? "Saved changes." : "Created successfully.");
      onSaved?.(saved);
    } catch (err: any) {
      setError(err?.message || "Failed to save.");
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async () => {
    if (!isEdit || !initial?.id) return;
    if (!confirm("Delete this model? This action cannot be undone.")) return;
    setDeleting(true);
    setError(null);
    try {
      const url = `${apiBaseUrl}/${initial.id}`;
      const res = await fetch(url, { method: "DELETE" });
      if (!res.ok) throw new Error(`DELETE ${url} failed with ${res.status}`);
      setSuccess("Deleted.");
      onDeleted?.(initial.id);
    } catch (err: any) {
      setError(err?.message || "Failed to delete.");
    } finally {
      setDeleting(false);
    }
  };

  return (
    <div className="mx-auto max-w-3xl p-6">
      <div className="mb-6 flex items-center justify-between">
        <h1 className="text-2xl font-semibold text-gray-900">
          {isEdit ? "Edit ML Model" : "Create ML Model"}
        </h1>
        {isEdit && (
          <button
            type="button"
            onClick={handleDelete}
            disabled={deleting}
            className={dangerBtn}
            title="Delete"
          >
            {deleting ? "Deleting..." : "Delete"}
          </button>
        )}
      </div>

      <form onSubmit={handleSubmit} className="grid gap-6">
        {/* Name */}
        <div>
          <label className={labelBase} htmlFor="name">
            Name <span className="text-rose-600">*</span>
          </label>
          <input
            id="name"
            type="text"
            required
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="e.g. ResNet50"
            className={inputBase}
            maxLength={255}
          />
          <p className="mt-1 text-xs text-gray-500">Required. 255 chars max.</p>
        </div>

        {/* Full Name */}
        <div>
          <label className={labelBase} htmlFor="fullName">Full name</label>
          <input
            id="fullName"
            type="text"
            value={fullName ?? ""}
            onChange={(e) => setFullName(e.target.value)}
            placeholder="e.g. Residual Network 50 Layers"
            className={inputBase}
            maxLength={255}
          />
        </div>

        {/* Explain */}
        <div>
          <label className={labelBase} htmlFor="explain">Explain</label>
          <textarea
            id="explain"
            ref={explainRef}
            value={explain ?? ""}
            onChange={(e) => setExplain(e.target.value)}
            placeholder="Describe the model (up to 1000 characters)"
            className={`${inputBase} min-h-[120px] resize-none leading-6`}
            maxLength={1000}
          />
          <div className="mt-1 flex justify-end text-xs text-gray-500">
            {explain?.length || 0}/1000
          </div>
        </div>

        {/* Actions */}
        <div className="flex items-center gap-3">
          <button type="submit" className={primaryBtn} disabled={submitting}>
            {submitting ? (isEdit ? "Saving..." : "Creating...") : isEdit ? "Save" : "Create"}
          </button>
          <button
            type="button"
            onClick={() => {
              // reset to initial
              setName(initial?.name ?? "");
              setFullName(initial?.fullName ?? "");
              setExplain(initial?.explain ?? "");
              setError(null);
              setSuccess(null);
            }}
            className={secondaryBtn}
          >
            Reset
          </button>
        </div>

        {/* Alerts */}
        {error && (
          <div className="rounded-xl border border-rose-200 bg-rose-50 px-4 py-3 text-rose-700">
            {error}
          </div>
        )}
        {success && (
          <div className="rounded-xl border border-emerald-200 bg-emerald-50 px-4 py-3 text-emerald-700">
            {success}
          </div>
        )}
      </form>
    </div>
  );
}
