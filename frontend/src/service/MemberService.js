import AxoisInstance from './AxiosInstance';

export async function createUser(data) {
  try {
    return await AxoisInstance.post("/member/add-member", data);
  } catch (e) {
    return e;
  }
}

export async function getAllMember() {
  try {
    return await await AxoisInstance.get("/member/find-all");
  } catch (e) {
    return e;
  }
}

export async function createDebt(data) {
  try {
    return await AxoisInstance.post("/debt/add-debt", data);
  } catch (e) {
    return e;
  }
}

export async function getListDebt(data) {
  try {
    return await AxoisInstance.post("/debt/search", data);
  } catch (e) {
    return e;
  }
}

export async function cashBack(idMember, price) {
  try {
    return await AxoisInstance.get(`/history/cash-back/${idMember}/${price}`);
  } catch (e) {
    return e;
  }
}

export async function deleteHistory(idHistory) {
  try {
    return await AxoisInstance.delete(`/history/${idHistory}`);
  } catch (e) {
    return e;
  }
}

export async function deleteDebt(idDebt) {
  try {
    return await AxoisInstance.delete(`/debt/${idDebt}`);
  } catch (e) {
    return e;
  }
}

export async function getAgency() {
  try {
    return await AxoisInstance.get(`/member/get-agency`);
  } catch (e) {
    return e;
  }
}

export async function getMemberOf(idMember) {
  try {
    return await AxoisInstance.get(`/member/get-member-of/${idMember}`);
  } catch (e) {
    return e;
  }
}

export async function statisticalSearch(condition) {
  try {
    return await AxoisInstance.post('/statistical/search', condition);
  } catch (e) {
    return e;
  }
}
export async function deleteMember(id) {
  try {
    return await AxoisInstance.delete(`/member/${id}`);
  } catch (e) {
    return e;
  }
}